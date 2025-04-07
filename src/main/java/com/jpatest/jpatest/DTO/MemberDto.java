package com.jpatest.jpatest.DTO;


import com.jpatest.jpatest.Entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class MemberDto {

    private String memberId;
    private String password;
    private String name;
    private String email;
    private String tel;

    private static ModelMapper modelMapper = new ModelMapper();

    static{
        modelMapper.typeMap(MemberDto.class, Member.class).addMappings(mapper ->{
                                mapper.skip(Member :: setId); //dto와 entity
                                            });
    }


    // Entity -> DTO
    public static MemberDto of(Member member){
        return  modelMapper.map(member, MemberDto.class);
    }

    // DTO -> Entity
    public Member createMember(){
        return modelMapper.map(this , Member.class);
    }


}
