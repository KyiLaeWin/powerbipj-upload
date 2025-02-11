/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.upgpaint.powerbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.upgpaint.powerbi.service.CostCenterService;
import com.upgpaint.powerbi.service.SalesVolumeService;
import com.upgpaint.powerbi.service.VBPAService;
import com.upgpaint.powerbi.service.MMService;
import com.upgpaint.powerbi.service.MaterialService;
@SpringBootApplication
public class PowerBiMain implements CommandLineRunner{

	private static Logger log = LoggerFactory.getLogger(PowerBiMain.class);
	private CostCenterService costCenterService;
	private SalesVolumeService salesVolumeService;
	private VBPAService vpaService;
	private MMService mmService;
	private MaterialService materialService;
	
	@Autowired
	public PowerBiMain(@Qualifier("costCenterService") CostCenterService costCenterService, 
			@Qualifier("salesVolumeService") SalesVolumeService salesVolumeService,
			@Qualifier("vpaService")VBPAService vpaService,
			@Qualifier("mmService")MMService mmService,
			@Qualifier("materialService")MaterialService materialService
			
			){
		super();
		this.costCenterService = costCenterService;
		this.salesVolumeService = salesVolumeService;
		this.vpaService=vpaService;
		this.mmService=mmService;
		this.materialService=materialService;
	}
   
	public static void main(String[] args) {
		SpringApplication.run(PowerBiMain.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
	    log.info("Starting......");

	    if (args.length > 0) {
	        String serviceToRun = args[0];

	        switch (serviceToRun.toLowerCase()) {
	            case"all":
	        	costCenterService.getCostCenterFromSAP();
	        	salesVolumeService.getSalesVolumeFromSAP();
	        	vpaService.getVBPAFromSAP();
	        	mmService.getMMFromSAP();
	        	materialService.getMaterialFromSAP();
	        	break;
	        	
	            case "costcenter":
	                costCenterService.getCostCenterFromSAP();
	                break;

	            case "salesvolume":
	                salesVolumeService.getSalesVolumeFromSAP();
	                break;
	            case"vbpa" :
	            	vpaService.getVBPAFromSAP();
	            	break;
	            case"mm" :
	            	mmService.getMMFromSAP();
	            	break;
	            case"material" :
	            	materialService.getMaterialFromSAP();
	            	break;

	            default:
	                log.warn("Unknown service: {}", serviceToRun);
	                break;
	        }
	    } else {
	        log.warn("No service specified. Please provide 'costcenter' or 'salesvolume'.");
	    }

	    log.info("Finish......");
	    System.exit(0);
	}

}