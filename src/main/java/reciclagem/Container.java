package reciclagem;

public class Container {

	private Integer idContainer;
	private Integer[] tipoContainer = { 0, 0, 0, 0 };
	private boolean caminhao = false;

	/**
	 * 0 - PAPEL 1 - VIDRO 2 - METAL 4 - PLASTICO
	 */

	public Container(Integer idContainer) {
		this.idContainer = idContainer;
	}

	public Integer getIdContainer() {
		return idContainer;
	}

	public void setIdContainer(Integer idContainer) {
		this.idContainer = idContainer;
	}

	public Integer[] getTipoContainer() {
		return tipoContainer;
	}

	public boolean containerIsFull() {
		if (tipoContainer[0] == 0 || tipoContainer[1] == 0 || tipoContainer[2] == 0 || tipoContainer[3] == 0) {
			return false;
		}
		return true;
	}

	public void setTipoContainer(Integer[] tipoContainer) {
		this.tipoContainer = tipoContainer;
	}

	public boolean isCaminhao() {
		return caminhao;
	}

	public void setCaminhao(boolean caminhao) {
		this.caminhao = caminhao;
	}
}
