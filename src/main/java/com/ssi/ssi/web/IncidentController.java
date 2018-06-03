package com.ssi.ssi.web;

import com.ssi.ssi.common.response.rest.SuccessRestResponse;
import com.ssi.ssi.domain.model.Incident;
import com.ssi.ssi.resources.IncidentResource;
import com.ssi.ssi.service.IncidentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Incident")
@Api(value = "Incident", description = "Incidents")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @RequestMapping(
            method = RequestMethod.GET
    )
    public List<Incident> findAllIncident() {

        List<Incident> incidentList = new ArrayList<>();

        incidentService.getAllIncident().forEach(
                incident -> incidentList.add(incident)
        );

        return incidentList;

    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/createIncident"
    )
    public ResponseEntity<Incident> createIncident(@RequestBody IncidentResource incidentResource) {
        return new ResponseEntity<Incident>(incidentService.createIncident(incidentResource), HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/updateIncident"
    )
    public ResponseEntity<Incident> updateIncident(@RequestBody IncidentResource incidentResource) {

        Boolean wasUpdated = incidentService.updateIncident(incidentResource);
        if (wasUpdated) {
            Optional<Incident> incident = incidentService.findIncidentById(incidentResource.getIncidentId());
            return new ResponseEntity<Incident>(incident.get(), HttpStatus.OK);
        }

        return new ResponseEntity<Incident>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Delete incident")
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE
    )
    public SuccessRestResponse remove(@PathVariable Long id) {
        incidentService.delete(id);
        return new SuccessRestResponse();
    }

    @ApiOperation(value = "Get incident by Id")
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Incident> getById(@PathVariable Long id) {
        Optional<Incident> incidentDb = incidentService.findIncidentById(id);
        if (incidentDb.isPresent()) {
            return new ResponseEntity<Incident>(incidentDb.get(), HttpStatus.OK);
        }

        return new ResponseEntity<Incident>(HttpStatus.NOT_FOUND);
    }
}
