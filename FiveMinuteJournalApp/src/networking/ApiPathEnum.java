package networking;

public enum ApiPathEnum {
	SIGN_UP("/signup");

	private final String path;

	private ApiPathEnum(String path) {
		this.path = path;
	}

	public String getPath() {
		return this.path;
	}
}
