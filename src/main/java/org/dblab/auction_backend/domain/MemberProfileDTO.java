package org.dblab.auction_backend.domain;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class MemberProfileDTO {
    private String checkUser;
    private int id;
    private String profile_img;
    private MultipartFile new_profile_img;
}
