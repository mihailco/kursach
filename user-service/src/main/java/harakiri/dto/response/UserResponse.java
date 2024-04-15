package harakiri.dto.response;

import harakiri.entity.UserType;
import lombok.Data;

@Data
public class UserResponse {
   private long id;
   private String fio;
   private String description;
   private String username;
   private String email;
   private UserType userType;

}
