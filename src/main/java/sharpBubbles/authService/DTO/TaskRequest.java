package sharpBubbles.authService.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {

    private String header;

    private String comment;

    private String category;

    private String plannedImplDate;

    private String notifications;

}
