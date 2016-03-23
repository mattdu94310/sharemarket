package fr.dauphine.sharemarket.error;
public class ShareMarketException extends Exception {
	private static final long serialVersionUID = 1L;

	public ShareMarketException(String msg) {
		super(msg);
	}
}