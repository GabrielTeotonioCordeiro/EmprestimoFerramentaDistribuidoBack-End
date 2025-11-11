package modelo;

import servico.IEmprestimo;
import dao.EmprestimoDAO;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Representa um emprestimo com id, id do amigo, id da ferramenta, data de
 * emprestimo e data de devolução.
 */
public class Emprestimo implements IEmprestimo {

    private int idEmprestimo;
    private int idAmigo;
    private int idFerramenta;
    private String dataEmprestimo;
    private String dataDevolucao;

    private static final Logger logger = Logger.getLogger(Emprestimo.class.getName());

    private EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    @Override
    public List<String[]> listaEmprestimo() throws RemoteException {
        List <String[]> resultado = new ArrayList<>();
        List <IEmprestimo> lista = emprestimoDAO.getListaEmprestimo();
        for(IEmprestimo emprestimo : lista){
            resultado.add(new String[]{String.valueOf(emprestimo.getIDEmprestimo()),
                String.valueOf(emprestimo.getIDAmigo()),
                String.valueOf(emprestimo.getIDFerramenta()),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao()});
        }
        return resultado;
    }

    /**
     * Construtor padrão para a classe Emprestimo. Inicializa o id do
     * emprestimo, o id do amigo(para quem foi emprestado), o id da
     * ferramenta(ferramenta que foi emprestada), data do emprestimo e data de
     * devolução vazios.
     */
    public Emprestimo() {
        this(0, 0, 0, "", "");
    }

    /**
     * Construtor para a classe Emprestimo. Inicializa o emprestimo com os id's
     * de Emprestimo, Amigo e ferramenta, Data de emprestimo e de devolução
     * fornecidos.
     *
     * @param idEmprestimo Id do emprestimo
     * @param idAmigo Para que amigo foi emprestada a ferramenta.
     * @param idFerramenta Que ferramenta foi emprestada.
     * @param dataEmprestimo Quando foi emprestado a ferramenta.
     * @param dataDevolucao Data de quando a ferramenta deve ser devolvida.
     */
    public Emprestimo(int idEmprestimo, int idAmigo, int idFerramenta, String dataEmprestimo, String dataDevolucao) {
        this.idEmprestimo = idEmprestimo;
        this.idAmigo = idAmigo;
        this.idFerramenta = idFerramenta;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    /**
     * Obtém od id do emprestimo.
     *
     * @return o id do emprestimo.
     *
     */
    @Override
    public int getIDEmprestimo() {
        return idEmprestimo;
    }

    /**
     * Define o id do emprestimo.
     *
     * @param idEmprestimo O id do emprestimo a ser definido.
     */
    @Override
    public void setIDEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    /**
     * Obtém para que amigo foi emprestado.
     *
     * @return para quem foi emprestado.
     *
     */
    @Override
    public int getIDAmigo() {
        return idAmigo;
    }

    /**
     * Define para quem foi emprestado.
     *
     * @param idAmigo O id do amigo a ser definido.
     */
    @Override
    public void setIDAmigo(int idAmigo) {
        this.idAmigo = idAmigo;
    }

    /**
     * Obtém od id da ferramenta.
     *
     * @return o id da ferramenta.
     *
     */
    @Override
    public int getIDFerramenta() {
        return idFerramenta;
    }

    /**
     * Define o id da ferramenta.
     *
     * @param idFerramenta O id da ferramenta a ser definido.
     */
    @Override
    public void setIDFerramenta(int idFerramenta) {
        this.idFerramenta = idFerramenta;
    }

    /**
     * Obtém a data do emprestimo.
     *
     * @return A data do emprestimo.
     */
    @Override
    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    /**
     * Define a data do emprestimo.
     *
     * @param dataEmprestimo A data do emprestimo a ser definida.
     */
    @Override
    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    /**
     * Obtém a data de devolução do emprestimo.
     *
     * @return A data de devolução do emprestimo.
     */
    @Override
    public String getDataDevolucao() {
        return dataDevolucao;
    }

    /**
     * Define a data de devolução do emprestimo.
     *
     * @param dataDevolucao A data de devolução do emprestimo a ser definida.
     */
    @Override
    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    /**
     * Insere um emprestimo no banco de dados.
     *
     * @param idAmigo O id do amigo a ser inserido.
     * @param idFerramenta O id da ferramenta a ser inserido.
     * @param dataEmprestimo Data do emprestimo a ser inserido.
     * @return true se a inserção for bem-sucedida, false caso contrário.
     */
    @Override
    public boolean insertEmprestimoDB(int idAmigo, int idFerramenta, String dataEmprestimo) {
        int maiorID = emprestimoDAO.maiorIDEmprestimo() + 1;
        Emprestimo emprestimo = new Emprestimo(maiorID, idAmigo, idFerramenta, dataEmprestimo, "");
        emprestimoDAO.insertEmprestimoDB(emprestimo);
        return true;

    }

    /**
     * Remove um emprestimo do banco de dados.
     *
     * @param idEmprestimo O ID do emprestimo a ser removido.
     * @return true se a remoção for bem-sucedida, false caso contrário.
     */
    @Override
    public boolean deleteEmprestimoDB(int idEmprestimo) {
        emprestimoDAO.deleteEmprestimoDB(idEmprestimo);
        return true;
    }

    /**
     * Atualiza um emprestimo no banco de dados.
     *
     * @param idEmprestimo O id do emprestimo a ser atualizado.
     * @param idAmigo O id do amigo a ser atualizado.
     * @param idFerramenta O id da ferramenta a ser atualizada.
     * @param dataEmprestimo A Data do emprestimo a ser atualizada.
     * @param dataDevolucao A Data de devolução da ferramenta a ser atualizada.
     * @return true se a inserção for bem-sucedida, false caso contrário.
     */
    @Override
    public boolean updateEmprestimoDB(int idEmprestimo, int idAmigo, int idFerramenta, String dataEmprestimo, String dataDevolucao) {
        logger.log(Level.INFO, "Data de devolução recebida: {0}", dataDevolucao);
        Emprestimo emprestimo = new Emprestimo(idEmprestimo, idAmigo, idFerramenta, dataEmprestimo, dataDevolucao);
        logger.log(Level.INFO, "Data de devolução do objeto emprestimo: {0}", emprestimo.getDataDevolucao());
        emprestimoDAO.updateEmprestimoDB(emprestimo);
        return true;
    }

    /**
     * Retorna um emprestimo do banco de dados com base no ID.
     *
     * @param idEmprestimo O ID do Emprestimo a ser recuperado.
     * @return O emprestimo recuperado do banco de dados.
     */
    @Override
    public Emprestimo retrieveEmprestimoDB(int idEmprestimo) {
        return emprestimoDAO.retrieveEmprestimoDB(idEmprestimo);
    }

    /**
     * Retorna o maior ID de emprestimo no banco de dados.
     *
     * @return O maior ID de emprestimo no banco de dados.
     */
    @Override
    public int maiorID() {
        return emprestimoDAO.maiorIDEmprestimo();
    }

    @Override
    public List<String[]> getListaEmprestimoAtivo() throws RemoteException {
        ArrayList<IEmprestimo> listaEmprestimoAtivo = new ArrayList<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<IEmprestimo> listaEmprestimo = emprestimoDAO.getListaEmprestimo();
            for (int i = 0; i < listaEmprestimo.size(); i++) {
                if ((!"null".equals(listaEmprestimo.get(i).getDataDevolucao())) && (!"".equals(listaEmprestimo.get(i).getDataDevolucao()))) {
                    String[] dataDevolucaoInvertida = listaEmprestimo.get(i).getDataDevolucao().split("-");
                    Date dataDevolucaoInvertido = sdf.parse(dataDevolucaoInvertida[2] + "-" + dataDevolucaoInvertida[1] + "-" + dataDevolucaoInvertida[0]);
                    Date dataAtual = sdf.parse(LocalDate.now() + "");
                    if (dataAtual.compareTo(dataDevolucaoInvertido) < 0) {
                        listaEmprestimoAtivo.add(listaEmprestimo.get(i));
                    }
                } else {
                    listaEmprestimoAtivo.add(listaEmprestimo.get(i));
                }

            }
        } catch (ParseException erro) {
            logger.log(Level.SEVERE, "Erro: formato da data inválido", erro);
        }
        List<String[]> resultado = new ArrayList<>();
        for (IEmprestimo a : listaEmprestimoAtivo) {
            resultado.add(new String[]{String.valueOf(a.getIDEmprestimo()),
                String.valueOf(a.getIDAmigo()),
                String.valueOf(a.getIDFerramenta()),
                a.getDataEmprestimo(),
                a.getDataDevolucao()});
        }
        return resultado;
    }

    public List<IEmprestimo> getListaEmprestimoAtivoIEmprestimo() throws RemoteException {
        ArrayList<IEmprestimo> listaEmprestimoAtivo = new ArrayList<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<IEmprestimo> listaEmprestimo = emprestimoDAO.getListaEmprestimo();
            for (int i = 0; i < listaEmprestimo.size(); i++) {

                if ((!"null".equals(listaEmprestimo.get(i).getDataDevolucao())) && (!"".equals(listaEmprestimo.get(i).getDataDevolucao()))) {
                    String[] dataDevolucaoInvertida = listaEmprestimo.get(i).getDataDevolucao().split("-");
                    Date dataDevolucaoInvertido = sdf.parse(dataDevolucaoInvertida[2] + "-" + dataDevolucaoInvertida[1] + "-" + dataDevolucaoInvertida[0]);
                    Date dataAtual = sdf.parse(LocalDate.now() + "");
                    if (dataAtual.compareTo(dataDevolucaoInvertido) < 0) {
                        listaEmprestimoAtivo.add(listaEmprestimo.get(i));

                    }
                } else {
                    listaEmprestimoAtivo.add(listaEmprestimo.get(i));
                }

            }
        } catch (ParseException erro) {
            logger.log(Level.SEVERE, "Erro: formato da data inválido", erro);
            return listaEmprestimoAtivo;
        }
        return listaEmprestimoAtivo;
    }

    @Override
    public String emprestimoAtivo(int idEmprestimo) throws RemoteException {
        String ativo = "Não";
        Emprestimo emp = new Emprestimo();
        List<String[]> listaEmprestimoAtivo = emp.getListaEmprestimoAtivo();
        for (int i = 0; i < listaEmprestimoAtivo.size(); i++) {
            if (Integer.parseInt(listaEmprestimoAtivo.get(i)[0]) == idEmprestimo) {
                ativo = "Sim";
            }
        }
        return ativo;
    }

    /**
     * Retorna uma lista de emprestimos.
     *
     * @return Uma lista de emprestimos.
     */
}
