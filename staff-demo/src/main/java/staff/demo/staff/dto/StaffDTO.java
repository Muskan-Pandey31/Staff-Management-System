package staff.demo.staff.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StaffDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Long salary;
    private String email;
}
