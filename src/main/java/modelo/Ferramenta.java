package modelo;

import servico.IFerramenta;
import dao.EmprestimoDAO;
import dao.FerramentaDAO;
import java.rmi.RemoteException;
import java.util.List;

public class Ferramenta implements IFerramenta {

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
    @Override
    public String getNomeFerramenta() {
        return nomeFerramenta;
    }

    @Override
    public String buscarNomePorId(int id) {
        FerramentaDAO dao = new FerramentaDAO();
        List<Ferramenta> listaFerramenta = dao.getListaFerramenta();
        for (Ferramenta f : listaFerramenta) {
            if (f.getIdFerramenta() == id) {
                return f.getNomeFerramenta();
            }
        }
        return "";
    }

    @Override
    public void setNomeFerramenta(String nome) {
        this.nomeFerramenta = nome;
    }

    /**
     * Obtém o custo da ferramenta.
     *
     * @return O custo da ferramenta.
     */
    @Override
    public double getCustoFerramenta() {
        return custoFerramenta;
    }

    @Override
    public void setCustoFerramenta(double custo) {
        this.custoFerramenta = custo;
    }

    /**
     * metodos de banco de dados
     */
    @Override
    public boolean salvar() {
        FerramentaDAO dao = new FerramentaDAO();
        return dao.insertFerramentaDB(this);
    }

    @Override
    public boolean atualizar() {
        FerramentaDAO dao = new FerramentaDAO();
        return dao.updateFerramentaDB(this);
    }

    @Override
    public boolean deletar() {
        FerramentaDAO dao = new FerramentaDAO();
        return dao.deleteFerramentaDB(this.idFerramenta);
    }

// Método estático, porque não depende de uma instância
    @Override
    public Ferramenta buscarPorId(int id) {
        FerramentaDAO dao = new FerramentaDAO();
        return dao.retrieveFerramentaDB(id);
    }

// Também pode ser estático
    @Override
    public List<String[]> listarTodas() throws RemoteException {
        FerramentaDAO dao = new FerramentaDAO();
        List<IFerramenta> lista = dao.getListaFerramenta();
        List<String[]> resultado = null;
        for (IFerramenta ferramenta : lista) {
            resultado.add(new String[]{(ferramenta.getIdFerramenta() + ""), ferramenta.getNomeFerramenta(), ferramenta.getMarcaFerramenta(), (ferramenta.getCustoFerramenta() + "")});
        }
        return resultado;
    }

// E para gerar um novo ID quando criar uma ferramenta nova
    @Override
    public int proximoID() {
        FerramentaDAO dao = new FerramentaDAO();
        return dao.maiorIDFerramenta() + 1;
    }

    @Override
    public boolean insertFerramentaDB(String nome, String marca, double custo) {
        Ferramenta ferramenta = new Ferramenta(proximoID(), nome, custo, marca);
        return ferramenta.salvar();
    }

    @Override
    public boolean updateFerramentaDB(int id, String nome, String marca, double custo) {
        Ferramenta ferramenta = new Ferramenta(id, nome, custo, marca);
        return ferramenta.atualizar();
    }

    @Override
    public boolean deleteFerramentaDB(int id) {
        Ferramenta ferramenta = new Ferramenta();
        ferramenta.setIdFerramenta(id);
        return ferramenta.deletar();
    }

    @Override
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

    @Override
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

    @Override
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
    @Override
    public String getMarcaFerramenta() {
        return marcaFerramenta;
    }

    @Override
    public void setMarcaFerramenta(String marca) {
        this.marcaFerramenta = marca;
    }

    @Override
    public int getIdFerramenta() {
        return idFerramenta;
    }

    @Override
    public void setIdFerramenta(int idFerramenta) {
        this.idFerramenta = idFerramenta;
    }

}
