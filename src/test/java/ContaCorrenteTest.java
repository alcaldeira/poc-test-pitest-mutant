import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ContaCorrenteTest {

	private GerenciadoraContas gerContas;
	
	@Test
	public void testTedTransfer() {
		int idConta01 = 1;
		int idConta02 = 2;
		ContaCorrente conta01 = new ContaCorrente(idConta01, 200, true);
		ContaCorrente conta02 = new ContaCorrente(idConta02, 0, true);

		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);
		
		gerContas = new GerenciadoraContas(contasDoBanco);

		boolean sucesso = gerContas.transfereValor(idConta01, 100, idConta02);

		assertTrue(sucesso);
		assertThat(conta02.getSaldo(), is(100.0));
		assertThat(conta01.getSaldo(), is(100.0));
	}
	
	@Test
	public void testTransferInsufficientBalance() {

		int idConta01 = 1;
		int idConta02 = 2;
		ContaCorrente conta01 = new ContaCorrente(idConta01, 100, true);
		ContaCorrente conta02 = new ContaCorrente(idConta02, 0, true);
		
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);
		
		gerContas = new GerenciadoraContas(contasDoBanco);

		boolean sucesso = gerContas.transfereValor(idConta01, 200, idConta02);
		
		assertTrue(sucesso);
		assertThat(conta01.getSaldo(), is(-100.0));
		assertThat(conta02.getSaldo(), is(200.0));
	}

	@Test
	public void testTedTransferseNgativeBalance() {
		int idConta01 = 1;
		int idConta02 = 2;
		ContaCorrente conta01 = new ContaCorrente(idConta01, -100, true);
		ContaCorrente conta02 = new ContaCorrente(idConta02, 0, true);
		
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);

		gerContas = new GerenciadoraContas(contasDoBanco);

		boolean sucesso = gerContas.transfereValor(idConta01, 200, idConta02);
		
		assertTrue(sucesso);
		assertThat(conta01.getSaldo(), is(-300.0));
		assertThat(conta02.getSaldo(), is(200.0));
	}
	
	@Test
	public void testTedTransfersNegativeBalanceValueToNegative() {
		int idConta01 = 1;
		int idConta02 = 2;
		ContaCorrente conta01 = new ContaCorrente(idConta01, -100, true);
		ContaCorrente conta02 = new ContaCorrente(idConta02, -100, true);
		
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);
		
		gerContas = new GerenciadoraContas(contasDoBanco);

		boolean sucesso = gerContas.transfereValor(idConta01, 200, idConta02);
		
		/* ========== Verifica��es ========== */
		assertTrue(sucesso);
		assertThat(conta01.getSaldo(), is(-300.0));
		assertThat(conta02.getSaldo(), is(100.0));
	}
	
	@Test
	public void testTransferValueNone() {
		int idConta01 = 1;
		int idConta02 = 2;
		ContaCorrente conta01 = new ContaCorrente(idConta01, -100, true);
		ContaCorrente conta02 = new ContaCorrente(idConta02, -100, true);
		
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);
		
		gerContas = new GerenciadoraContas(contasDoBanco);

		boolean sucesso = gerContas.transfereValor(idConta01, 2, idConta02);
		
		assertTrue(sucesso);
		assertThat(conta01.getSaldo(), is(-102.0));
		assertThat(conta02.getSaldo(), is(-98.0));
	}
}
