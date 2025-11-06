package modelo;

import dao.AmigoDAO;
import java.util.ArrayList;
import java.util.List;
import service.EmprestimoService;

/**
 * Representa um amigo com id, nome e telefone.
 */
public class Amigo {

    private int idAmigo;
    private String nomeAmigo;
    private String telefone;
    
    private AmigoDAO amigoDAO = new AmigoDAO();
    
    /**
     * Construtor padrão para a classe Amigo. Inicializa o amigo com id, nome e
     * telefone vazios.
     */
    public Amigo() {
        this(0, "", "");
    }

    /**
     * Construtor para a classe Amigo. Inicializa o amigo com o id, nome e
     * telefone fornecidos.
     *
     * @param idAmigo O id do amigo
     * @param nomeAmigo O nome do amigo.
     * @param telefone O telefone do amigo.
     */
    public Amigo(int idAmigo, String nomeAmigo, String telefone) {
        this.idAmigo = idAmigo;
        this.nomeAmigo = nomeAmigo;
        this.telefone = telefone;
    }

    /**
     * Obtém o nome do amigo.
     *
     * @return O nome do amigo.
     */
    public String getNomeAmigo() {
        return nomeAmigo;
    }

    /**
     * Define o nome do amigo.
     *
     * @param nomeAmigo O nome do amigo a ser definido.
     */
    public void setNomeAmigo(String nomeAmigo) {
        this.nomeAmigo = nomeAmigo;
    }

    /**
     * Obtém o telefone do amigo.
     *
     * @return O telefone do amigo.
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do amigo.
     *
     * @param telefone O telefone do amigo a ser definido.
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Obtém o ID do amigo.
     *
     * @return O ID do amigo.
     */
    public int getIdAmigo() {
        return idAmigo;
    }

    /**
     * Define o ID do amigo.
     *
     * @param idAmigo O ID do amigo a ser definido.
     */
    public void setIdAmigo(int idAmigo) {
        this.idAmigo = idAmigo;
    }
    
    public List<Amigo> listarTodos() {
        return amigoDAO.getListaAmigo();
    }
    
    public boolean insertAmigoDB(String nome, String telefone) {
        int maiorID = amigoDAO.maiorIDAmigo() + 1;
        Amigo amigo = new Amigo(maiorID, nome, telefone);
        amigoDAO.insertAmigoDB(amigo);
        return true;
    }
    
    public boolean deleteAmigoDB(int id) {
        amigoDAO.deleteAmigoDB(id);
        return true;
    }
    
    public boolean updateAmigoDB(int id, String nome, String telefone) {
        Amigo amigo = new Amigo(id, nome, telefone);
        amigoDAO.updateAmigoDB(amigo);
        return true;
    }
    
    public Amigo retrieveAmigoDB(int id) {
        return amigoDAO.retrieveAmigoDB(id);
    }
    
    public int maiorID() {
        return amigoDAO.maiorIDAmigo();
    }
    
    public static List<Emprestimo> buscarEmprestimosDoAmigo(int id, boolean apenasAtivos) {
        EmprestimoService empService = new EmprestimoService();
        List<Emprestimo> lista = apenasAtivos ? empService.getListaEmprestimoAtivo()
                : empService.listaEmprestimo();
        
        List<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo e : lista) {
            if (e.getIDAmigo() == id) {
                resultado.add(e);
            }
        }
        return resultado;
    }
    
    public boolean possuiEmprestimoAtivo(int id) {
        return !buscarEmprestimosDoAmigo(id, true).isEmpty();
    }
    
    public int quantidadeDeEmprestimos(int id) {
        return buscarEmprestimosDoAmigo(id, false).size();
    }
    
    public String obterNomePorId(int id) {
        for (Amigo a : listarTodos()) {
            if (a.getIdAmigo() == id) {
                return a.getNomeAmigo();
            }
        }
        return "";
    }

}
