package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.LibraryMemberSeedDto;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.LibraryMemberService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class LibraryMemberServiceImpl implements LibraryMemberService {

    public static final String FILE_PATH_LIBRARY_MEMBERS = "src/main/resources/files/json/library-members.json";
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final LibraryMemberRepository libraryMemberRepository;
    private final ValidationUtil validationUtil;

    public LibraryMemberServiceImpl(ModelMapper modelMapper, Gson gson, LibraryMemberRepository libraryMemberRepository, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.libraryMemberRepository = libraryMemberRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return libraryMemberRepository.count()>0;
    }

    @Override
    public String readLibraryMembersFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH_LIBRARY_MEMBERS));
    }

    @Override
    public String importLibraryMembers() throws IOException {
        StringBuilder sb = new StringBuilder();

        LibraryMemberSeedDto[] libraryMemberSeedDto1 = gson.fromJson(readLibraryMembersFileContent(),LibraryMemberSeedDto[].class);

        Arrays.stream(libraryMemberSeedDto1)
                .filter(libraryMemberSeedDto -> {
                    Optional<LibraryMember> libraryMemberOptional = libraryMemberRepository.findByProneNumber(libraryMemberSeedDto.getPhoneNumber());
                    boolean isValid = validationUtil.isValid(libraryMemberSeedDto) && libraryMemberOptional.isEmpty();

                    if (isValid){
                        sb.append(String.format("Successfully imported library member %s - %s\n"
                        ,libraryMemberSeedDto.getFirstName(),libraryMemberSeedDto.getLastName()));
                    }else {
                        sb.append("Invalid library member\n");
                    }

                    return isValid;})
                .map(libraryMemberSeedDto ->{

                         LibraryMember libraryMember = modelMapper.map(libraryMemberSeedDto, LibraryMember.class);
                         libraryMember.setProneNumber(libraryMemberSeedDto.getPhoneNumber());
                         libraryMember.setFirstName(libraryMemberSeedDto.getFirstName());
                         libraryMember.setLastName(libraryMemberSeedDto.getLastName());
                         libraryMember.setAddress(libraryMemberSeedDto.getAddress());
                         return libraryMember;
                })
                .forEach(libraryMemberRepository::save);

        return sb.toString();
    }
}
