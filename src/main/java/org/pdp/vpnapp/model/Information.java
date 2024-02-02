package org.pdp.vpnapp.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Information {
    private String ip;
    private String country_name;
    private String region_name;
    private String city;
    private String zip;
    private String latitude;
    private String longitude;
}
