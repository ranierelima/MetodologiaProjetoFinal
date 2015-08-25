package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

//classe principal
public class Principal {

	private int c,i;
	private String caminho;
	private String arquivo;
	private JFrame tela;
	private JPanel superior;
	private JPanel botoes;
	private JPanel centro;
	private JPanel status;
	private JTextField textNome;
	private JLabel lNome;
	private JLabel lStatus;
	private JButton btnCompactar;
	private JButton btnDescompactar;
	private JButton sair;
	private String diretorio = new String("ArquivosCompactados/");
 
	public static void main(String[] args) {
	Principal principal = new Principal();
	principal.TelaUsuario();
}
	//Inicialização da Tela do Usuario, quando o sistem chama todos os metodos
		private void TelaUsuario() {
			preparaJFrameTelaInicial("RunZIP");
			preparaTelaSuperior();
			preparaTelaBotoes();
			preparaTelaTabela();
			preparaTelaStatus();
			labelNome("Nome: ");
			labelStatus("STATUS: ...");
			tFieldNome();
			btnSelecionarArquivo("Selecione o Arquivo");
			btnCompactar("Compactar");
			btnSair("Sair");
			btnDescompactar("Descompactar");
			mostraTelaInicial();
			mostraTelaSuperior();
			mostraTelaBotoes();
		//	mostraTelaTabela();
			mostraTelaStatus();
			}
		
		
				//Botão Sair
		private void btnSair(String tituloBotao) {
			sair=new JButton(tituloBotao);
			sair.addActionListener(new ActionListener() {  
	                      public void actionPerformed(ActionEvent e){  
	                             System.exit(0);  
	                          }  
	                  });
			botoes.add(sair);
		}
		
		//Label da mensagem de Status
		private void labelStatus(String tituloLabel) {
			lStatus = new JLabel(tituloLabel);
			status.add(lStatus);
		}
		
		//Campo aonde aparece o Nome do arquivo
		private void labelNome(String tituloLabel) {
			lNome= new JLabel(tituloLabel);
			superior.add(lNome);
		}
		
		//TextField para a mensagem "Nome:"
		private void tFieldNome() {
			textNome = new JTextField(50);
			superior.add(textNome);
		}
		
		//Botão para Selecionar o Arquivo
		private void btnSelecionarArquivo(String tituloBotao) {
			JButton btnSelArquivo = new JButton(tituloBotao);
		
			btnSelArquivo.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					SelecionarArquivo();
				}
			});
			
			botoes.add(btnSelArquivo);
		}
		
		//Metodo que seleciona o arquivo
		private void SelecionarArquivo() {
			
			JFileChooser selArquivo = new JFileChooser();	
			
			int returnValue = selArquivo.showOpenDialog(tela);
			
			System.out.printf("returnValue = %d\n", returnValue);
			System.out.printf("caminho = %s\n", selArquivo.getSelectedFile());
			
			caminho=selArquivo.getSelectedFile().getAbsolutePath();
			
			System.out.println(caminho); 
			
			textNome.setText(selArquivo.getSelectedFile().getName());
			//arquivo.setText(selArquivo.getSelectedFile().getName());
			//System.out.println(arquivo+" Aqui é pra ser o arquivo");
			
			if(returnValue == JFileChooser.APPROVE_OPTION) {
				arquivo = selArquivo.getSelectedFile().getName();  
				System.out.println(arquivo);
                
				System.out.println("Encontrou algum arquivo.");
				lStatus.setText("STATUS: Arquivo encontrado");
			} else {
				System.out.println("Não encontrou arquivo algum.");
				lStatus.setText("STATUS: Nenhum arquivo selecionado");
			}
		}
		
		//Botão para compactar
				private void btnCompactar(String tituloBotao) {
					btnCompactar = new JButton(tituloBotao);
					btnCompactar.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								compactacao();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}			}

						
					});
					botoes.add(btnCompactar);
				}
				
				private void compactacao() throws IOException {
					try {       File inFolder = new File("ArquivosCompactados/");
					System.out.println(caminho);
				       File outFolder = new File("ArquivosCompactados/");  
				       BufferedReader in = new BufferedReader(new FileReader(caminho));
				       BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(diretorio+arquivo+".gz")));
				       while((c=in.read()) !=-1)
							out.write(c);
						in.close();
						out.close();
				       System.out.println("Até aqui foi");
				       lStatus.setText("STATUS: O seu arquivo já foi compactado");
					
					
				} catch(IOException e1){
					}
				}
		
				
				
				private void btnDescompactar(String tituloBotao) {
					btnDescompactar = new JButton(tituloBotao);
				
					btnDescompactar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
						}
					});
			}
				
				
								
		//Preparação da Parte Grafica
		
		private void preparaJFrameTelaInicial(String NomeSistema) {
			tela = new JFrame(NomeSistema);
			tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}
		
		private void preparaTelaSuperior() {
			superior = new JPanel();
		}
		
		private void preparaTelaBotoes() {
			botoes = new JPanel();
			}

		private void preparaTelaTabela() {
			centro = new JPanel();
			centro.setSize(200,200);
			String[] colunas = {"ID","Nome do Arquivo","Caminho do Arquivo","Ações"}; 
			Object[][] dados = {
					 {i,arquivo,caminho,btnDescompactar}
					 };
			
			
			
		
			
			}
		
		private void preparaTelaStatus() {
			status = new JPanel();
		}

		
		//Exibição da Parte Gráfica
		private void mostraTelaSuperior() {
			tela.add(superior, BorderLayout.NORTH);
		}
		
		private void mostraTelaBotoes() {
			tela.add(botoes);
			
		}

		private void mostraTelaStatus() {
			tela.add(status,BorderLayout.SOUTH);
		}

		private void mostraTelaInicial() {
		tela.setSize(800, 550);
		tela.setVisible(true);
		}



}
