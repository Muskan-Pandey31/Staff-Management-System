package staff.demo.staff.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import staff.demo.staff.dto.StaffDTO;
import staff.demo.staff.entity.StaffEntity;
import staff.demo.staff.repository.StaffRepository;

import java.util.List;

@Service
public class StaffServiceImplementation implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<StaffDTO> getAllStaff() {
        List<StaffEntity> staffEntityList = staffRepository.findAll();
        List<StaffDTO> staffDTOList = staffEntityList
                .stream()
                .map(staffEntity -> modelMapper.map(staffEntity, StaffDTO.class))
                .toList();
        return staffDTOList;
    }

    @Override
    public StaffDTO getStaffByID(Long id) {
        StaffEntity staffEntity = staffRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Staff Not Found With This Id: " + id));
        return modelMapper.map(staffEntity, StaffDTO.class);

    }

    @Override
    public StaffDTO createNewStaff(StaffDTO staffDTO) {
        staffDTO.setId(null);
        StaffEntity newStaffEntity = modelMapper.map(staffDTO, StaffEntity.class);
        StaffEntity staffEntity = staffRepository.save(newStaffEntity);
        return modelMapper.map(staffEntity, StaffDTO.class);

    }

    @Override
    public void deleteStaffById(Long id) {
        if (!staffRepository.existsById(id)){
            throw new IllegalArgumentException("Staff Doesn't Exist With This Id: " + id);

        } else {
            staffRepository.deleteById(id);

        }
    }


    @Override
    public StaffDTO updateStaffWholeData(Long id, StaffDTO staffDTO) {
        StaffEntity staffEntity = staffRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Staff Not Found With This Id: " + id));

        // If ID is changed, we have to remove old entity and save new one

        staffDTO.setId(id);
        if (!id.equals(staffDTO.getId())) {
            staffRepository.deleteById(id); // delete old record
            StaffEntity newEntity = modelMapper.map(staffDTO, StaffEntity.class);
            StaffEntity saved = staffRepository.save(newEntity); // save new record with new ID
            return modelMapper.map(saved, StaffDTO.class);
        }

        // If ID is same, just update other fields

        modelMapper.map(staffDTO, staffEntity);
        StaffEntity saved = staffRepository.save(staffEntity);
        return modelMapper.map(saved, StaffDTO.class);

    }
}
