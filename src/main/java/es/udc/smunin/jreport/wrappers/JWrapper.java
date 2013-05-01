package es.udc.smunin.jreport.wrappers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.udc.smunin.jreport.net.APIHttpClient;
import es.udc.smunin.jreport.net.DataParser;
import es.udc.smunin.jreport.wrappers.datamodels.Service;

/**
 * Wraps an endpoint and allows users to get data from it without caring about
 * 
 * @author Santiago Munín <santimunin@gmail.com>
 * 
 */
public class JWrapper {

	private final static String PARAM_JURISDICTION_ID = "jurisdiction_id";
	private String cityName;
	private String baseUrl;
	private String jurisdictionId;

	public JWrapper(String cityName, String baseUrl, String jurisdictionId) {
		super();
		this.cityName = cityName;
		this.baseUrl = baseUrl;
		this.jurisdictionId = jurisdictionId;
	}

	public List<Service> getServices() throws IOException {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(PARAM_JURISDICTION_ID, jurisdictionId);
		try {
			String httpResponse = APIHttpClient.getInstance().doGet(baseUrl,
					parameters);
			return DataParser.getInstance().parseServicesList(httpResponse);
		} catch (URISyntaxException e) {
		}
		// TODO
		return null;

	}

	public String toString() {
		return "Wrapper of the " + cityName + " city.";
	}
}
