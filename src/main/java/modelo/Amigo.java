package modelo;

import dao.AmigoDAO;
import java.util.ArrayList;
import java.util.List;

public class Amigo implements IAmigo {

    /**
     * Representa um amigo com id, nome e telefone.
     */
    private int idAmigo;
    private String nomeAmigo;
    private String telefone;

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
    @Override
    public String getNomeAmigo() {
        return nomeAmigo;
    }

    /**
     * Define o nome do amigo.
     *
     * @param nomeAmigo O nome do amigo a ser definido.
     */
    @Override
    public void setNomeAmigo(String nomeAmigo) {
        this.nomeAmigo = nomeAmigo;
    }

    /**
     * Obtém o telefone do amigo.
     *
     * @return O telefone do amigo.
     */
    @Override
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do amigo.
     *
     * @param telefone O telefone do amigo a ser definido.
     */
    @Override
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Obtém o ID do amigo.
     *
     * @return O ID do amigo.
     */
    @Override
    public int getIdAmigo() {
        return idAmigo;
    }

    /**
     * Define o ID do amigo.
     *
     * @param idAmigo O ID do amigo a ser definido.
     */
    @Override
    public void setIdAmigo(int idAmigo) {
        this.idAmigo = idAmigo;
    }

    @Override
    public List<Amigo> listarTodos() {
        AmigoDAO dao = new AmigoDAO();
        return dao.getListaAmigo();
    }

    @Override
    public boolean insertAmigoDB(String nome, String telefone) {
        AmigoDAO dao = new AmigoDAO();
        int maiorID = dao.maiorIDAmigo() + 1;
        Amigo amigo = new Amigo(maiorID, nome, telefone);
        dao.insertAmigoDB(amigo);
        return true;
    }

    @Override
    public boolean deleteAmigoDB(int id) {
        AmigoDAO dao = new AmigoDAO();
        dao.deleteAmigoDB(id);
        return true;
    }

    @Override
    public boolean updateAmigoDB(int id, String nome, String telefone) {
        AmigoDAO dao = new AmigoDAO();
        Amigo amigo = new Amigo(id, nome, telefone);
        dao.updateAmigoDB(amigo);
        return true;
    }

    @Override
    public Amigo retrieveAmigoDB(int id) {
        AmigoDAO dao = new AmigoDAO();
        return dao.retrieveAmigoDB(id);
    }

    @Override
    public int maiorID() {
        AmigoDAO dao = new AmigoDAO();
        return dao.maiorIDAmigo();
    }

    @Override
    public List<Emprestimo> buscarEmprestimosDoAmigo(int id, boolean apenasAtivos) {
        Emprestimo emp = new Emprestimo();
        List<Emprestimo> lista = apenasAtivos ? emp.getListaEmprestimoAtivo()
                : emp.listaEmprestimo();

        List<Emprestimo> resultado = new ArrayList<>();
        for (Emprestimo e : lista) {
            if (e.getIDAmigo() == id) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    @Override
    public boolean possuiEmprestimoAtivo(int id) {
        return !buscarEmprestimosDoAmigo(id, true).isEmpty();
    }

    @Override
    public int quantidadeDeEmprestimos(int id) {
        return buscarEmprestimosDoAmigo(id, false).size();
    }

    @Override
    public String obterNomePorId(int id) {
        for (Amigo a : listarTodos()) {
            if (a.getIdAmigo() == id) {
                return a.getNomeAmigo();
            }
        }
        return "";
    }

}
