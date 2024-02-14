package br.com.pagamento.entity.tipotransacao;

public enum TipoTransacaoEnum {
	
	DEBITO("D", "DÉBITO"), 
	CREDITO("C", "CRÉDITO");
	
	private String key;
	private String value;
	
	TipoTransacaoEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
