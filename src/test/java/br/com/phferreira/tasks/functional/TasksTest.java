package br.com.phferreira.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	
	public WebDriver setupApplication() throws MalformedURLException {
//		WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();	
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.15.161:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.15.161:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	@Test
	public void salvarTaskComSucesso() throws MalformedURLException {
		WebDriver driver = setupApplication();
		
		try {
			//Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever task
			driver.findElement(By.id("task")).sendKeys("Teste Funcional");
			
			//Escrever data
			driver.findElement(By.id("dueDate")).sendKeys("01/01/2028");
			
			//Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
		}finally {
			//Fechar browser
			driver.quit();
		}
	}
	
	@Test
	public void naoSalvarTaskSemDescricao() throws MalformedURLException {
		WebDriver driver = setupApplication();
		
		try {
			//Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever task
			//driver.findElement(By.id("task")).sendKeys("Test Error");
			
			//Escrever data
			driver.findElement(By.id("dueDate")).sendKeys("01/01/2001");
			
			//Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar mensagem de erro
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
		}finally {
			//Fechar browser
			driver.quit();
		}
	}
	
	@Test
	public void naoSalvarTaskComDataPassada() throws MalformedURLException {
		WebDriver driver = setupApplication();
		
		try {
			//Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever task
			driver.findElement(By.id("task")).sendKeys("Test error date past");
			
			//Escrever data
			driver.findElement(By.id("dueDate")).sendKeys("01/01/2001");
			
			//Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar mensagem de erro
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
		}finally {
			//Fechar browser
			driver.quit();
		}
	}
	
	@Test
	public void naoSalvarTaskSemData() throws MalformedURLException {
		WebDriver driver = setupApplication();
		
		try {
			//Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//Escrever task
			driver.findElement(By.id("task")).sendKeys("Test error no date");
			
			//Escrever data
			//driver.findElement(By.id("dueDate")).sendKeys("01/01/2001");
			
			//Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//Validar mensagem de erro
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
		}finally {
			//Fechar browser
			driver.quit();
		}
	}
}