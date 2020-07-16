import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClienteTest {
    private GerenciadoraClientes gerenciadorCliente;
    private int idCLiente01 = 1;
    private	int idCLiente02 = 2;
    private	int idCLiente03 = 3;

    @Before
    public void setUp() {
        Cliente cliente01 = new Cliente(idCLiente01, "Anderson Caldeira", 26, "anderson@gmail.com", 1, true);
        Cliente cliente02 = new Cliente(idCLiente02, "Joyce Caldeira", 34, "joyce@gmail.com", 1, true);

        List<Cliente> clientesDoBanco = new ArrayList<>();
        clientesDoBanco.add(cliente01);
        clientesDoBanco.add(cliente02);

        gerenciadorCliente = new GerenciadoraClientes(clientesDoBanco);
    }

    @Test
    public void testGetCliente() {
        Cliente cliente = gerenciadorCliente.pesquisaCliente(idCLiente01);

        assertThat(cliente.getId(), is(idCLiente01));
    }
    
    @Test
    public void testRemoveCliente() {
        boolean clienteRemovido = gerenciadorCliente.removeCliente(idCLiente02);

        assertThat(clienteRemovido, is(true));
        assertThat(gerenciadorCliente.getClientesDoBanco().size(), is(1));
        assertNull(gerenciadorCliente.pesquisaCliente(idCLiente02));
    }

    @After
    public void tearDown() {
        gerenciadorCliente.limpa();
    }
}
