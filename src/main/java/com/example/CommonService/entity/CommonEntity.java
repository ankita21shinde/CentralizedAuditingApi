package com.example.CommonService.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name ="CentralizedApiTable")
public class CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String serviceName;
    private String apiEndPoint;
    private String apiStatusCode;

    //extra fields
    private String requestTime;
    private String responseTime;
    private int StatusCode;
    private String timeTaken;
    private String requestURI;
    private String requestMethod;
    private String requestHeaderName;
    private String contentType;
    private String requestID;
    private String hostName;
    private String response;
    private String errorTrace;

}
