package tests.olx.pagesobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public void carregarPaginaInicial() {
        driver.get("https://www.olx.com.br/");
    }
    public String obterTituloPagina() {
        return driver.getTitle();
    }
}
