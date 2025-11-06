package modelo;

import dao.EmprestimoDAO;
import dao.FerramentaDAO;
import java.util.List;

public class Ferramenta {

    private int idFerramenta;
    private String nomeFerramenta;
    private String marcaFerramenta;
    private double custoFerramenta;

    /**
     * Construtor para a classe Ferramenta. Inicializa a ferramenta com nome e
     * custo, define a marca como uma string vazia e define a disponibilidade
     * como verdadeira.
     *
     * @param nome O nome da ferramenta.
     * @param custo O custo da ferramenta.
     * @param idFerramenta O id da ferramenta.
     */
    public Ferramenta() {
        this(0, "", 0, "");
    }

    /**
     * public Ferramenta(int idFerramenta, String nome, double custo, String
     * marca) { this.idFerramenta = idFerramenta; this.nome = nome; this.custo =
     * custo; this.marca = marca; // Inicializa a marca como uma string vazia
     * this.disponivel = true; this.dao = new FerramentaDAO(); }
     */
    public Ferramenta(int idFerramenta, String nome, double custo, String marca) {
        this.idFerramenta = idFerramenta;
        this.nomeFerramenta = nome;
        this.custoFerramenta = custo;
        this.marcaFerramenta = marca; // Inicializa a marca como uma string vazia

    }

    /**
     * Obtém o nome da ferramenta.
     *
     * @return O nome da ferramenta.
     */
    public String getNomeFerramenta() {
        return nomeFerramenta;
    }
    
    public static String buscarNomePorId(int id) {
    FerramentaDAO dao = new FerramentaDAO();
    List<Ferramenta> listaFerramenta = dao.getListaFerramenta();
    for (Ferramenta f : listaFerramenta) {
        if (f.getIdFerramenta() == id) {
            return f.getNomeFerramenta();
        }
    }
    return "";
}

    public void setNomeFerramenta(String nome) {
        this.nomeFerramenta = nome;
    }

    /**
     * Obtém o custo da ferramenta.
     *
     * @return O custo da ferramenta.
     */
    public double getCustoFerramenta() {
        return custoFerramenta;
    }

    public void setCustoFerramenta(double custo) {
        this.custoFerramenta = custo;
    }

    /**
     * metodos de banco de dados
     */
    public boolean salvar() {
        FerramentaDAO dao = new FerramentaDAO();
        return dao.insertFerramentaDB(this);
    }

    public boolean atualizar() {
        FerramentaDAO dao = new FerramentaDAO();
        return dao.updateFerramentaDB(this);
    }

    public boolean deletar() {
        FerramentaDAO dao = new FerramentaDAO();
        return dao.deleteFerramentaDB(this.idFerramenta);
    }

// Método estático, porque não depende de uma instância
    public static Ferramenta buscarPorId(int id) {
        FerramentaDAO dao = new FerramentaDAO();
        return dao.retrieveFerramentaDB(id);
    }

// Também pode ser estático
    public static List<Ferramenta> listarTodas() {
        FerramentaDAO dao = new FerramentaDAO();
        return dao.getListaFerramenta();
    }

// E para gerar um novo ID quando criar uma ferramenta nova
    public static int proximoID() {
        FerramentaDAO dao = new FerramentaDAO();
        return dao.maiorIDFerramenta() + 1;
    }

    public boolean insertFerramentaDB(String nome, String marca, double custo) {
        Ferramenta ferramenta = new Ferramenta(Ferramenta.proximoID(), nome, custo, marca);
        return ferramenta.salvar();
    }

    public boolean updateFerramentaDB(int id, String nome, String marca, double custo) {
        Ferramenta ferramenta = new Ferramenta(id, nome, custo, marca);
        return ferramenta.atualizar();
    }

    public boolean deleteFerramentaDB(int id) {
        Ferramenta ferramenta = new Ferramenta();
        ferramenta.setIdFerramenta(id);
        return ferramenta.deletar();
    }

    public String getDisponivel() {
        EmprestimoDAO empDAO = new EmprestimoDAO();
        List<Emprestimo> emprestimosAtivos = empDAO.getListaEmprestimo();

        for (Emprestimo e : emprestimosAtivos) {
            if (e.getIDFerramenta() == this.idFerramenta) {
                return "Não";
            }
        }
        return "Sim";
    }

    public boolean possuiEmprestimoAtivo() {
        EmprestimoDAO empDAO = new EmprestimoDAO();
        List<Emprestimo> emprestimosAtivos = empDAO.getListaEmprestimo();

        for (Emprestimo e : emprestimosAtivos) {
            if (e.getIDFerramenta() == this.idFerramenta) {
                return true;
            }
        }
        return false;
    }

    public int quantidadeEmprestimo() {
        EmprestimoDAO empDAO = new EmprestimoDAO();
        List<Emprestimo> emprestimos = empDAO.getListaEmprestimo();

        int contador = 0;
        for (Emprestimo e : emprestimos) {
            if (e.getIDFerramenta() == this.idFerramenta) {
                contador++;
            }

        }
        return contador;
    }

    /**
     * Verifica se a ferramenta está disponível.
     *
     * @return {@code true} se a ferramenta estiver disponível, {@code false}
     * caso contrário.
     */
    /**
     * Obtém a marca da ferramenta.
     *
     * @return A marca da ferramenta.
     */
    public String getMarcaFerramenta() {
        return marcaFerramenta;
    }

    public void setMarcaFerramenta(String marca) {
        this.marcaFerramenta = marca;
    }

    public int getIdFerramenta() {
        return idFerramenta;
    }

    public void setIdFerramenta(int idFerramenta) {
        this.idFerramenta = idFerramenta;
    }

}
