package com.springcard.restcontroller;

import com.springcard.common.CarrierServiceFactory;
import com.springcard.model.CarrierServiceIDS;
import com.springcard.model.PackageDetails;
import com.springcard.model.Shipment;
import com.springcard.common.CommonUtils;
import com.springcard.service.ShipmentService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShipmentRestController.class)
public class ShipmentControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CarrierServiceFactory carrierServiceFactory;

	private final static String SHIPMENT_SERVICE_URL = "/api/shipments/";

	@Autowired
	private WebApplicationContext context;

	@Test
	public void WhenShipmentWithInValidCarrierIdThenReturnBadRequestStatusCode() throws Exception {
		PackageDetails packageDetails = createPackageDetails("1234fd", 33, 100, 12, 22);
		mockMvc.perform( MockMvcRequestBuilders
						.post(SHIPMENT_SERVICE_URL + "fedexai")
						.content(CommonUtils.asJson(createShipment(CarrierServiceIDS.carrierServiceIDSValue("fedexair"), packageDetails)))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", Matchers.containsString("Invalid carrer service id")));
	}

	@Test
	public void WhenShipmentWithoutSKUIdThenReturnBadRequestStatusCode() throws Exception {
		PackageDetails packageDetails = createPackageDetails(null, 33, 100, 12, 22);
		mockMvc.perform( MockMvcRequestBuilders
						.post(SHIPMENT_SERVICE_URL + "fedexair")
						.content(CommonUtils.asJson(createShipment(CarrierServiceIDS.carrierServiceIDSValue("fedexair"), packageDetails)))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", Matchers.containsString("Shipment info isn't provided!")));
	}

	@Test
	public void WhenShipmentWithoutPackageDetailsThenReturnBadRequestStatusCode() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
						.post(SHIPMENT_SERVICE_URL + "fedexair")
						.content(CommonUtils.asJson(createShipment(CarrierServiceIDS.carrierServiceIDSValue("fedexair"), null)))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", Matchers.containsString("Shipment info isn't provided!")));
	}
	@Test
	@Ignore
	public void WhenShipmentWithValidCarrierIdThenReturnSuccessfulMessage() throws Exception {
		PackageDetails packageDetails = createPackageDetails("1234fd", 33, 100, 12, 22);
		mockMvc.perform( MockMvcRequestBuilders
						.post(SHIPMENT_SERVICE_URL + "fedexair")
						.content(CommonUtils.asJson(createShipment(CarrierServiceIDS.carrierServiceIDSValue("fedexair"), packageDetails)))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().string("Shipment has been placed successfully"));
	}

	private Shipment createShipment(CarrierServiceIDS carrierServiceIDS, PackageDetails packageDetails) {
		Shipment shipment = new Shipment();
		shipment.setCarrierServiceId(carrierServiceIDS);
		shipment.setPackageDetails(packageDetails);
		return shipment;
	}

	private PackageDetails createPackageDetails(String skuId, double width,
			double height, final double weight, final double length) {
		PackageDetails packageDetails = new PackageDetails();
		packageDetails.setHeight(height);
		packageDetails.setLength(length);
		packageDetails.setWidth(width);
		packageDetails.setWeight(weight);
		packageDetails.setSkuId(skuId);
		return packageDetails;
	}

}
