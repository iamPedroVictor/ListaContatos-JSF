package br.com.edu.fametro.dao;

import br.com.edu.fametro.entidade.Contato;
import br.com.edu.fametro.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ContatoDao {

    private Session session;
    
    public ContatoDao(){
        setSession();
    }
    
    private void setSession(){
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    public void SalvarOuEditar(Contato contato){
        if(contato.getId() == null){
            Salvar(contato);
        }else{
            Editar(contato);
        }
    }
    
    public void Salvar(Contato contato){
        setSession();
        this.session.getTransaction().begin();
        this.session.save(contato);
        this.session.getTransaction().commit();
        this.session.close();
    }
    
    public void Editar(Contato contato){
        setSession();
        this.session.getTransaction().begin();
        this.session.update(contato);
        this.session.getTransaction().commit();
        this.session.close();
    }
    
    public void Excluir(Contato contato){
        setSession();
        this.session.getTransaction().begin();
        this.session.delete(contato);
        this.session.getTransaction().commit();
        this.session.close();
    }
    
    public Contato BuscarById(int id){
        Query sql = this.session.createQuery("from Contato where id = :codigoId");
        sql.setInteger("codigoId", id);
        Contato contato = (Contato)sql.uniqueResult();
        this.session.close();
        return contato;
    }
    
    public List<Contato> ListarContatos(){         
        this.session = HibernateUtil.getSessionFactory().openSession();  
        Query sql = session.createQuery("from Contato");
        return sql.list();
    }
    
    public List<Contato> ListarContatosPorNome(){         
        this.session = HibernateUtil.getSessionFactory().openSession();  
        Query sql = session.createQuery("from Contato ORDER BY nome");
        return sql.list();
    }
    
}
