package ir.maktab.dtos;

/**
 * @author : Bahar Zolfaghari
 **/
public class ProfilePictureDto {
    private Integer id;
    private String name;
    private byte[] data;
    private SpecialistDto specialistDto;

    public Integer getId() {
        return id;
    }

    public ProfilePictureDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProfilePictureDto setName(String name) {
        this.name = name;
        return this;
    }

    public byte[] getData() {
        return data;
    }

    public ProfilePictureDto setData(byte[] data) {
        this.data = data;
        return this;
    }

    public SpecialistDto getSpecialistDto() {
        return specialistDto;
    }

    public ProfilePictureDto setSpecialistDto(SpecialistDto specialistDto) {
        this.specialistDto = specialistDto;
        return this;
    }
}
