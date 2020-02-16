package ar.com.nssa.monitoreo.domain;

public class FileUri extends Uri {
	private String file;

	public FileUri(String sUri, String sUri2) {
		this.value = sUri;
		this.file = sUri2;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	@Override
	public boolean isFile(String url) {
		return true;
	}

}
