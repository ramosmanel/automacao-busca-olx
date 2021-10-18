package tests.olx.cucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.cucumber.messages.internal.com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import tests.olx.pagesobjects.HomePage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FazerPesquisaSteps {
    private WebDriver driver;
    private List<WebElement> listaProdutos;

    //By
    private By verificarLogout = By.cssSelector(".sc-hSdWYo > .sc-bxivhb");
    private By barraDePesquisa = By.cssSelector("#searchtext");
    private By clicarPesquisa = By.cssSelector(".searchSubmitBtn");
    private By itensDeBusca = By.className("sc-1mbetcw-0");
    private By guia_2 = By.cssSelector("[data-lurker_position='2'] .sc-1koxwg0-2");
    private By guia_5 = By.cssSelector("[data-lurker_position='5'] .sc-1koxwg0-2");
    private By guia_8 = By.cssSelector("[data-lurker_position='8'] .sc-1koxwg0-2");
    //Globais
    HomePage homePage;

    //Variáveis
    String resultadoPesquisa;

    //Esperado
    String deslogadoPaginaInicial = "Entrar";
    String tituloPagina = "Olx o maior site de compra de venda do Brasil";

    @Before
    public void inicializar() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\marcos.ramos\\drivers\\chromedriver(94).exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //Pré-condição
    @Dado("que estou na pagina inicial da olx")
    public void que_estou_na_pagina_inicial_da_olx() {
        homePage = new HomePage(driver);
        homePage.carregarPaginaInicial();
        assertThat(tituloPagina.toLowerCase(), is("Olx o maior site de compra de venda do Brasil".toLowerCase()));
    }

    @Quando("nao me encontro logado")
    public void nao_me_encontro_logado() {
        assertThat(driver.findElement(verificarLogout).getText(), is(deslogadoPaginaInicial));
    }

    @Dado("pesquiso o produto {string} na barra de pesquisa")
    public void pesquiso_o_produto_na_barra_de_pesquisa(String nomeProduto) {
        driver.findElement(barraDePesquisa).sendKeys(nomeProduto);
        driver.findElement(clicarPesquisa).click();
    }

    //Resultados Esperados
    @Entao("eu escolho o produto {string} no {int} para compra-lo")
    public void eu_escolho_o_produto_para_compra_lo(String nomeProduto, Integer indiceProduto) {

        listaProdutos = driver.findElements(itensDeBusca);
        System.out.println("Resultados encontrados: " + listaProdutos.size());
        System.out.println("Itens encontrados: ");

        for (int i = 0; i != listaProdutos.size() ; i++){
            resultadoPesquisa = listaProdutos.get(i).getText().toLowerCase();
            if(resultadoPesquisa.toLowerCase().contains(nomeProduto.toLowerCase())) {
                System.out.println(resultadoPesquisa);
            }else{
                System.out.println("(" + resultadoPesquisa + ")");
            }
        }
        listaProdutos.get(indiceProduto).click();
    }

    //Validando as Pesquisas
    @Quando("pesquiso pelo produto {string} na barra de pesquisa")
    public void pesquiso_pelo_produto_na_barra_de_pesquisa(String nomeProduto) {
        driver.findElement(barraDePesquisa).sendKeys(nomeProduto);
        driver.findElement(clicarPesquisa).click();
    }

    @Entao("o produto {string} deve estar presente no resultado da busca")
    public void o_produto_deve_estar_presente_no_resultado_da_busca(String nomeProduto) {
        listaProdutos = driver.findElements(itensDeBusca);
        System.out.println("Resultados encontrados: " + listaProdutos.size());
        System.out.println("Itens encontrados: ");

        for (int i = 0; i != listaProdutos.size() ; i++){
            resultadoPesquisa = listaProdutos.get(i).getText().toLowerCase();
            if(resultadoPesquisa.toLowerCase().contains(nomeProduto.toLowerCase())) {
                System.out.println(resultadoPesquisa);
            }else{
                System.out.println("(" + resultadoPesquisa + ")");
            }
        }
    }

    @Entao("eu clico na segunda aba")
    public void eu_clico_na_segunda_aba() {
       driver.findElement(guia_2).click();
    }

    @Entao("eu clico na quinta aba")
    public void eu_clico_na_quinta_aba() {
        driver.findElement(guia_5).click();
    }


    @Entao("eu clico na oitava aba")
    public void eu_clico_na_oitava_aba() {
        driver.findElement(guia_8).click();
    }

    @After (order = 1)
    public void capturarTela(Scenario scenario) {
        TakesScreenshot camera = (TakesScreenshot) driver;
        File capturaDeTela = camera.getScreenshotAs(OutputType.FILE);

        String scenarioId = scenario.getId().substring(scenario.getId().lastIndexOf(".feature:") + 9);
        String nomeArquivo = "src/test/java/tests/olx/screenshots/" + scenarioId + "_" + scenario.getStatus() + ".png";
        System.out.println(nomeArquivo);

        try {
            Files.move(capturaDeTela, new File(nomeArquivo));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @After (order = 0)
    public void finalizar() {
        driver.close();
        driver.quit();
    }

}
