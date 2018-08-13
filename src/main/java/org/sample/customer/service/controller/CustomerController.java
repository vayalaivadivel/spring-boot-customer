package org.sample.customer.service.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.sample.customer.service.dao.CustomerDao;
import org.sample.customer.service.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author vadivel
 *
 */
@RestController
@RequestMapping(value = "/customer")
@Api(value = "Customer Service", description = "CRUD with Mysql", tags = { "Customer Service" })
public class CustomerController {
	private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private CustomerDao customerDao;

	@ApiOperation(produces = MediaType.APPLICATION_JSON_VALUE, response = List.class, responseContainer = "List", value = "Get All")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Invalid Request"),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal Server Error") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> getAll() {
		LOG.info("Retrieving all customer... GET /customer");
		return customerDao.findAll();
	}

	@ApiOperation(produces = MediaType.ALL_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, value = "Create")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Created (no response content)"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Invalid Request"),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal Server Error") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST)
	public String save(@RequestBody final Customer customer) {
		LOG.info("Saving customer... POST /customer");
		LOG.debug("Content of customer: {}", customer);
		final Customer e = customerDao.save(customer);
		return String.format("Records saved successfully with the id %s", e.getName());

	}

	@ApiOperation(produces = MediaType.ALL_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, value = "Delete")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Information not found"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Invalid request"),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal server error") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.DELETE)
	public String delete(@RequestParam("name") final Optional<String> name) {
		LOG.info("Deleting customer by name... DELETE /customer");
		customerDao.deleteById(name.get());
		return "Record deleted successfully";
	}

	@ApiOperation(produces = MediaType.ALL_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, value = "Update")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Updated (no response content)"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Invalid Request"),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal Server Error") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT)
	public String update(@RequestBody final Customer customer) {
		LOG.info("Updating customer info... PUT /customer");
		LOG.debug("Customer details: {}", customer);
		customerDao.save(customer);
		return "Record updated successfully";
	}

	@ApiOperation(produces = MediaType.APPLICATION_JSON_VALUE, response = List.class, responseContainer = "List", value = "Partial Name Search")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Invalid Request"),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal Server Error") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> search(@PathVariable("name") final Optional<String> name) {
		final String na = name.get();
		LOG.info("Getting customer by name... GET /customer/{}", na);
		return customerDao.searchByName(na);

	}
}
