package harakiri.dto.response;

import harakiri.entity.UserType;
import lombok.Data;

@Data
public class UserResponse {
   long id;
   String fio;
   String description;
   String username;
   UserType userType;

}
