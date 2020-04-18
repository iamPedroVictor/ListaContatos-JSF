package br.com.edu.fametro.beans;

import br.com.edu.fametro.dao.ContatoDao;
import br.com.edu.fametro.entidade.Contato;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ContatoBean {

    private Contato contato = new Contato();
    private List<Contato> contatos = new ArrayList<>();
    
    private final ContatoDao contatoDao = new ContatoDao();
    private boolean isAlfabetica = false;
    
    @PostConstruct
    public void Limpar(){
        this.contato = new Contato();
        if(isAlfabetica){
            ListarByNome();
        }else{
            Listar();
        }
    }
    
    public void Salvar(){
        contatoDao.SalvarOuEditar(contato);
        Limpar();
        if(isAlfabetica){
            ListarByNome();
        }else{
            Listar();
        }
    }
    
    public void Editar(Contato contatoP){
        this.contato = contatoP;
    }
    
    public void Excluir(Contato contatoP){
        contatoDao.Excluir(contatoP);
        if(isAlfabetica){
            ListarByNome();
        }else{
            Listar();
        }
    }
    
    public void ListarByNome(){
        this.contatos = contatoDao.ListarContatosPorNome();
        isAlfabetica = true;
    }
    
    public void Listar(){
        this.contatos = contatoDao.ListarContatos();
        isAlfabetica = false;
    }
    
    public Contato getContato(){
        return contato;
    }
    
    public void setContato(Contato contato){
        this.contato = contato;
    }
    
    public List<Contato> getContatos(){
        return contatos;
    }
    
    public void setContatos(List<Contato> contatos){
        this.contatos = contatos;
    }
    
}
