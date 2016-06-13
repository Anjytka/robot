package npp.robot.services;

import npp.robot.core.Place;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseService {

    public static final Logger log = LoggerFactory.getLogger(BaseService.class);

    public static Place place = Place.getInstance();

}
