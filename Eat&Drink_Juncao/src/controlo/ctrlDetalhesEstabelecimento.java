package controlo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import dados.Estabelecimento;
import dados.Prato;
import dados.PratoLOG;

public class ctrlDetalhesEstabelecimento {
	
	private Prato prato;
	private PratoLOG pratoLOG;
	
	public ctrlDetalhesEstabelecimento() {
		prato = new Prato();
		pratoLOG = new PratoLOG();
	}
	
	/**
	 * Return the establishment with the given ID
	 * 
	 * @return establishment ID
	 */
	public Estabelecimento consultarDetalhesEstabelecimento(int establishmentID) {
		Estabelecimento establishmentDataObject = new Estabelecimento();
		return establishmentDataObject.select(establishmentID);
	}

	public boolean insere(int idEstabelecimento, Prato p) {
		return prato.insere(idEstabelecimento, p);
	}

	public void removePrato(int idPrato) {
		new Prato().remove(idPrato);
	}

	public void exportPratoLog() {
		ArrayList<PratoLOG> pratoLOGg = pratoLOG.select();
		try
		{
		    FileWriter writer = new FileWriter("halo.csv");
			
		    writer.append("preco");
		    writer.append(',');
		    writer.append("idPrato");
		    writer.append(',');
		    writer.append("descricao");
		    writer.append(',');
		    writer.append("tipoDePrato");
		    writer.append(',');
		    writer.append("data");
		    writer.append(',');
		    writer.append("operacao");
		    writer.append(',');
		    writer.append("utilizador");
		    writer.append(System.getProperty("line.separator"));
		    
		    
		    for (PratoLOG pratoLOG : pratoLOGg) {
		    	writer.append(pratoLOG.getPreco() + "");
			    writer.append(',');
			    writer.append(pratoLOG.getIdPrato() + "");
			    writer.append(',');
			    writer.append(pratoLOG.getDescricao());
			    writer.append(',');
			    writer.append(pratoLOG.getTipoDePrato() + "");
			    writer.append(',');
			    writer.append(pratoLOG.getData().toString());
			    writer.append(',');
			    writer.append(pratoLOG.getTipoDeOperacao());
			    writer.append(',');
			    writer.append(pratoLOG.getUtilizador());
			    writer.append(System.getProperty("line.separator"));
			}
	 
	 
		    //generate whatever data you want
	 
		    writer.flush();
		    writer.close();
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} 
		
	}

}
