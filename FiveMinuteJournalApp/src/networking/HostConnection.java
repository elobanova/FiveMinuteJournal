package networking;

public class HostConnection {
	public static final String HOST_ADDRESS = "https://akirsanov.com";
	public static final String PORT = "8181";

	public String getFullAddress() {
		return HOST_ADDRESS + ":" + PORT;
	}
}
