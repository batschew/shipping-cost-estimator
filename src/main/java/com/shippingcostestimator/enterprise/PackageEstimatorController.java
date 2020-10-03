package com.shippingcostestimator.enterprise;

import com.shippingcostestimator.enterprise.dto.Shipment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // Decides what renders when a user hits a URL or endpoint
public class PackageEstimatorController {
    /**
     * Handle the root ("/") endpoint and return a start page.
     * @return
     */
    @RequestMapping("/")
    public String index() {
        Shipment shipment = new Shipment();
        
        return "start";
    }
}
