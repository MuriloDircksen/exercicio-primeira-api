package br.com.futurodev.api.primeiraapi.controllers;

import br.com.futurodev.api.primeiraapi.model.ProdutoModel1;
import br.com.futurodev.api.primeiraapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

    @Autowired // IC/CD/CDI injeção de dependencias
    private ProdutoRepository produtoRepository;


    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/mostrarvalor/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }

    @GetMapping(value = "/mostrarnome/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public String mostrarNome(@PathVariable String nome){
       return "Olá "+ nome;
    }

    @RequestMapping(value = "/produto/{descricao}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String salva(@PathVariable String descricao){
        ProdutoModel1 produto = new ProdutoModel1();
        produto.setDescricao(descricao);
        produtoRepository.save(produto); //gravar no banco de dados um produto

        return"Produto "+descricao+" registrado com sucesso!" ;
    }

    @GetMapping (value = "/produtos/")
    @ResponseBody //retorna os dados no corpo da resposta
    public ResponseEntity<List<ProdutoModel1>> listarProdutos(){

        List<ProdutoModel1> produtos = produtoRepository.findAll(); //consulta o banco de dados de todos os produtos

        return new ResponseEntity<List<ProdutoModel1>>(produtos, HttpStatus.OK); //retorna a lista do banco de dados em jason( javascript object)
    }

    @PostMapping(value = "/produto/salvar/") //mapeia a url
    @ResponseBody //descreve a resposta, informando que o retorno será no corpo da requisição
    public ResponseEntity<ProdutoModel1> salvar(@RequestBody ProdutoModel1 produto){ //recebe os dados para salvar

        ProdutoModel1 prod = produtoRepository.save(produto);
        return new ResponseEntity<ProdutoModel1>(prod, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/produto/deletar/")
    @ResponseBody
    public ResponseEntity<String> deletar(@RequestParam Long idProduto){ //recebe da requisição o parametro

        produtoRepository.deleteById(idProduto);

        return new ResponseEntity<String>("Produto deletado com sucesso", HttpStatus.OK);
    }
}
