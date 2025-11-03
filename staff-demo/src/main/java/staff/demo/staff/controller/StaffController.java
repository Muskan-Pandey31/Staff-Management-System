package staff.demo.staff.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import staff.demo.staff.config.MapperConfig;
import staff.demo.staff.dto.StaffDTO;
import staff.demo.staff.service.StaffService;
import staff.demo.staff.service.StaffServiceImplementation;

import java.util.List;

@RestController
@RequestMapping("/getStaffDetails")
@CrossOrigin
public class StaffController {

    @Autowired
    private StaffService staffService;


//  http://localhost:8080/getStaffDetails/getAllStaff

    @GetMapping("/getAllStaff")
    public ResponseEntity<List<StaffDTO>> getStaff(){
        return ResponseEntity.ok(staffService.getAllStaff());
    }

//  http://localhost:8080/getStaffDetails/getStaffById/1

    @GetMapping("/getStaffById/{id}")
    public ResponseEntity<StaffDTO> getStaffById(@PathVariable Long id){
        return ResponseEntity.ok(staffService.getStaffByID(id));
    }


//  http://localhost:8080/getStaffDetails

    @PostMapping
    public ResponseEntity<StaffDTO> createNewStaff(@RequestBody StaffDTO staffDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(staffService.createNewStaff(staffDTO));
    }

//  http://localhost:8080/getStaffDetails/1

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaffById(@PathVariable Long id){
        staffService.deleteStaffById(id);
        return ResponseEntity.noContent().build();
    }

//  http://localhost:8080/getStaffDetails/1

    @PutMapping("/{id}")
    public ResponseEntity<StaffDTO> updateStaffWholeData(@PathVariable Long id,
                                                         @RequestBody StaffDTO staffDTO){
        StaffDTO staffDTO1 = staffService.updateStaffWholeData(id, staffDTO);
        return ResponseEntity.ok( staffDTO1);

    }
}
