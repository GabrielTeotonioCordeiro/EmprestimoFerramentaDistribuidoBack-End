package principal;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import modelo.Amigo;
import modelo.Emprestimo;
import modelo.Ferramenta;
import modelo.IAmigo;
import modelo.IEmprestimo;
import modelo.IFerramenta;

public class Principal {

    public static void main(String[] args) {
        try {
            IAmigo ami = new Amigo();
            IEmprestimo emp = new Emprestimo();
            IFerramenta fer = new Ferramenta();
            IAmigo iAmi = (IAmigo) UnicastRemoteObject.exportObject((ami), 0);
            IEmprestimo iEmp = (IEmprestimo) UnicastRemoteObject.exportObject(emp, 0);
            IFerramenta iFer = (IFerramenta) UnicastRemoteObject.exportObject(fer, 0);
            Registry registroAmigo = LocateRegistry.createRegistry(1098);
            Registry registroEmprestimo = LocateRegistry.createRegistry(1099);
            Registry registroFerramenta = LocateRegistry.createRegistry(1100);
            
            registroAmigo.bind("AmigoService", iAmi);
            registroEmprestimo.bind("EmprestimoService", iEmp);
            registroFerramenta.bind("FerramentaService", iFer);
            System.out.println("Servidor no Ar. Nome do objeto Servidor: \' mensagens\'");
        } catch (Exception e) {
            System.out.println("Excessão: " + e);
        }
    }
}
