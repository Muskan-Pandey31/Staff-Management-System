package staff.demo.staff.service;

import staff.demo.staff.dto.StaffDTO;

import java.util.List;

public interface StaffService {
    List<StaffDTO> getAllStaff();
    StaffDTO getStaffByID(Long id);

    StaffDTO createNewStaff(StaffDTO staffDTO);

    void deleteStaffById(Long id);

    StaffDTO updateStaffWholeData(Long id, StaffDTO staffDTO);
}

