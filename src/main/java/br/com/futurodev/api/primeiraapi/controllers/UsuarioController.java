package br.com.futurodev.api.primeiraapi.controllers;

import br.com.futurodev.api.primeiraapi.Service.CadastroUsuarioService;
import br.com.futurodev.api.primeiraapi.dto.TelefoneRepresetationModel;
import br.com.futurodev.api.primeiraapi.dto.UsuarioRepresentationModel;
import br.com.futurodev.api.primeiraapi.input.UsuarioInput;
import br.com.futurodev.api.primeiraapi.model.TelefoneModel;
import br.com.futurodev.api.primeiraapi.model.UsuarioModel;
import br.com.futurodev.api.primeiraapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    /*@PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioModel> cadastrar(@RequestBody UsuarioModel usuario){
        UsuarioModel usu = usuarioRepository.save(usuario);
        return new ResponseEntity<UsuarioModel>(usu, HttpStatus.CREATED);
    }primeira alteração, inclusão do service*/
   /* @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioModel> cadastrar(@RequestBody UsuarioModel usuario) {
        UsuarioModel usu = cadastroUsuarioService.salvar(usuario);
        return new ResponseEntity<UsuarioModel>(usu, HttpStatus.CREATED);
    }implementação service*/

    /* @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioRepresentationModel> cadastrar(@RequestBody UsuarioModel usuario) {
        UsuarioModel usu = cadastroUsuarioService.salvar(usuario);
        return new ResponseEntity<UsuarioRepresentationModel>(toModel(usu), HttpStatus.CREATED);
    }implementação DTO*/

    //a entrada continua sendo um usuario model, porém a saida e uma DTO -UsuarioRepresentationModel

    @PostMapping(value = "/", produces = "application/json")
    @Transactional
    public ResponseEntity<UsuarioRepresentationModel> cadastrar(@RequestBody UsuarioInput usuarioInput) {
       //converte um usuario input em usuario model, interessante quando o metodo não
        // irá necessitar salvar dados
        UsuarioModel usu = cadastroUsuarioService.salvar(toDomainObject(usuarioInput));
        //converte usurio model em usurio representationmodel
        return new ResponseEntity<UsuarioRepresentationModel>(toModel(usu), HttpStatus.CREATED);
    }

    /*@PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioModel> atualizar(@RequestBody UsuarioModel usuario){
        UsuarioModel usu = usuarioRepository.save(usuario);
        return new ResponseEntity<UsuarioModel>(usu, HttpStatus.OK);
    }primeira alteração, inclusão do service*/
  /*  @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioModel> atualizar(@RequestBody UsuarioModel usuario) {
        UsuarioModel usu = cadastroUsuarioService.salvar(usuario);
        return new ResponseEntity<UsuarioModel>(usu, HttpStatus.OK);
    }implementação service*/

    /* @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioRepresentationModel> atualizar(@RequestBody UsuarioModel usuario) {
        UsuarioModel usu = cadastroUsuarioService.salvar(usuario);

        return new ResponseEntity<UsuarioRepresentationModel>(toModel(usu), HttpStatus.OK);
    }implementação DTO*/

    @PutMapping(value = "/", produces = "application/json")
    @Transactional
    public ResponseEntity<UsuarioRepresentationModel> atualizar(@RequestBody UsuarioInput usuarioInput) {
        UsuarioModel usu = cadastroUsuarioService.salvar(toDomainObject(usuarioInput));

        return new ResponseEntity<UsuarioRepresentationModel>(toModel(usu), HttpStatus.OK);
    }


    /*@DeleteMapping(value = "/")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idUsuario){
         usuarioRepository.deleteById(idUsuario);
         return new ResponseEntity<String>("Usuario deletado!", HttpStatus.OK);
    }primeira alteração, inclusão do service*/

    @DeleteMapping(value = "/")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> delete(@RequestParam Long idUsuario){
        cadastroUsuarioService.deletar(idUsuario);
        //não retorna um usuario model, não precisamos refatorar para usuariorepresentationmodel
        return new ResponseEntity<String>("Usuario deletado!", HttpStatus.OK);
    }

    /*@GetMapping(value = "/{idUsuario}", produces = "application/json")
    public ResponseEntity<UsuarioModel> getUserById(@PathVariable(value = "idUsuario") Long idUsuario){

        UsuarioModel usu = usuarioRepository.findById(idUsuario).get();

        return new ResponseEntity<UsuarioModel>(usu, HttpStatus.OK);
    }primeira alteração, inclusão do service*/
    /*@GetMapping(value = "/{idUsuario}", produces = "application/json")
    public ResponseEntity<UsuarioModel> getUserById(@PathVariable(value = "idUsuario") Long idUsuario){

        UsuarioModel usu = cadastroUsuarioService.getUserById(idUsuario);

        return new ResponseEntity<UsuarioModel>(usu, HttpStatus.OK);
    }segunda alteração, inserção do DTO*/

    @GetMapping(value = "/{idUsuario}", produces = "application/json")
    @Transactional
    public ResponseEntity<UsuarioRepresentationModel> getUserById(@PathVariable(value = "idUsuario") Long idUsuario){

        UsuarioModel usu = cadastroUsuarioService.getUserById(idUsuario);

        UsuarioRepresentationModel usuarioRepresentationModel = toModel(usu);


        return new ResponseEntity<UsuarioRepresentationModel>(usuarioRepresentationModel, HttpStatus.OK);
    }



    /*@GetMapping(value = "/buscarpornome", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<UsuarioModel>> getByUserName(String nome){
        List<UsuarioModel> usuarios = cadastroUsuarioService.getUserByName(nome);

        return new ResponseEntity<List<UsuarioModel>>(usuarios, HttpStatus.OK);
    }inclusão service*/
    @GetMapping(value = "/buscarpornome", produces = "application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<List<UsuarioRepresentationModel>> getByUserName(String nome){
        //obtem a lista de usuarios do tipo model
        List<UsuarioModel> usuarios = cadastroUsuarioService.getUserByName(nome);

        //convertemos a lista do objeto tipo usuario model para usuario representation model(DTO)
        List<UsuarioRepresentationModel> usuariosRepresentationModel=toColletionModel(usuarios);
        return new ResponseEntity<List<UsuarioRepresentationModel>>
                (usuariosRepresentationModel, HttpStatus.OK);
    }

    //metodo conversão objeto usuario model em usuariorepresentationmodel
    private UsuarioRepresentationModel toModel(UsuarioModel usu) {
        UsuarioRepresentationModel usuarioRepresentationModel=new UsuarioRepresentationModel();
        //realizar a conversão do UsuarioModel para representation model
        usuarioRepresentationModel.setId(usu.getId());
        usuarioRepresentationModel.setNome(usu.getNome());
        usuarioRepresentationModel.setLogin(usu.getLogin());
        usuarioRepresentationModel.setSenha(usu.getSenha());
        usuarioRepresentationModel.setDataCadastro(usu.getDataCadastro());
        usuarioRepresentationModel.setDataAtualizacao(usu.getDataAtualizacao());

        for(int i=0; i<usu.getTelefones().size();i++) {
            TelefoneRepresetationModel telefoneRepresetationModel = new TelefoneRepresetationModel();
            telefoneRepresetationModel.setId(usu.getTelefones().get(i).getId());
            telefoneRepresetationModel.setTipo(usu.getTelefones().get(i).getTipo());
            telefoneRepresetationModel.setNumero(usu.getTelefones().get(i).getNumero());

            usuarioRepresentationModel.getTelefones().add(telefoneRepresetationModel);
        }
        return usuarioRepresentationModel;
    }

    //convert uma lista de objeto usuario model em usuariorepresentationmodel
    private List<UsuarioRepresentationModel> toColletionModel(List<UsuarioModel> usuariosModel){
        return usuariosModel.stream()
                .map(usuarioModel ->toModel(usuarioModel)).collect(Collectors.toList());
    }

    //converter um objeto do tipo usuario input para usuario model
    private UsuarioModel toDomainObject(UsuarioInput usuarioInput){
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setLogin(usuarioInput.getLogin());
        usuarioModel.setId(usuarioInput.getId());
        usuarioModel.setNome(usuarioInput.getNome());
        usuarioModel.setSenha(usuarioInput.getSenha());



        for(int i=0; i<usuarioInput.getTelefones().size();i++) {
            TelefoneModel telefonesModel = new TelefoneModel();
            telefonesModel.setId(usuarioInput.getTelefones().get(i).getId());
            telefonesModel.setTipo(usuarioInput.getTelefones().get(i).getTipo());
            telefonesModel.setNumero(usuarioInput.getTelefones().get(i).getNumero());
            telefonesModel.setUsuario(usuarioModel);
            usuarioModel.getTelefones().add(telefonesModel);
        }


        return usuarioModel;

    }
}
