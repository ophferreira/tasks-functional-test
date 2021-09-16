package br.com.phferreira.tasks.functional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	public WebDriver setupApplication() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		
		return driver;
	}
	
	@Test
	public void salvarTaskComSucesso() {
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
	public void naoSalvarTaskSemDescricao() {
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
	public void naoSalvarTaskComDataPassada() {
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
	public void naoSalvarTaskSemData() {
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