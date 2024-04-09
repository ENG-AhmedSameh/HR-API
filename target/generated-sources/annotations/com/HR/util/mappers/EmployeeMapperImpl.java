package com.HR.util.mappers;

import com.HR.dto.BenefitDto;
import com.HR.dto.EmployeeBenefitDto;
import com.HR.dto.EmployeeDto;
import com.HR.persistence.entities.Benefit;
import com.HR.persistence.entities.Department;
import com.HR.persistence.entities.Employee;
import com.HR.persistence.entities.EmployeeBenefit;
import com.HR.persistence.entities.Team;
import com.HR.persistence.entities.entitiesIDs.EmployeebenefitId;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-09T03:26:24+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee toEntity(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setDirectManager( employeeDtoToEmployee( employeeDto ) );
        employee.setDepartment( employeeDtoToDepartment( employeeDto ) );
        employee.setTeam( employeeDtoToTeam( employeeDto ) );
        employee.setId( employeeDto.getId() );
        employee.setFirstName( employeeDto.getFirstName() );
        employee.setLastName( employeeDto.getLastName() );
        employee.setTotalVacationDaysAllotted( employeeDto.getTotalVacationDaysAllotted() );

        return employee;
    }

    @Override
    public EmployeeDto toDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setDirectManagerID( employeeDirectManagerId( employee ) );
        employeeDto.setDepartmentID( employeeDepartmentId( employee ) );
        employeeDto.setTeamID( employeeTeamId( employee ) );
        employeeDto.setId( employee.getId() );
        employeeDto.setFirstName( employee.getFirstName() );
        employeeDto.setLastName( employee.getLastName() );
        employeeDto.setTotalVacationDaysAllotted( employee.getTotalVacationDaysAllotted() );

        return employeeDto;
    }

    @Override
    public List<EmployeeDto> toDtoList(List<Employee> employees) {
        if ( employees == null ) {
            return null;
        }

        List<EmployeeDto> list = new ArrayList<EmployeeDto>( employees.size() );
        for ( Employee employee : employees ) {
            list.add( toDto( employee ) );
        }

        return list;
    }

    @Override
    public List<Employee> toEntityList(List<EmployeeDto> employeeDtos) {
        if ( employeeDtos == null ) {
            return null;
        }

        List<Employee> list = new ArrayList<Employee>( employeeDtos.size() );
        for ( EmployeeDto employeeDto : employeeDtos ) {
            list.add( toEntity( employeeDto ) );
        }

        return list;
    }

    @Override
    public Set<Employee> toEntitySet(Set<EmployeeDto> employeeDtos) {
        if ( employeeDtos == null ) {
            return null;
        }

        Set<Employee> set = new LinkedHashSet<Employee>( Math.max( (int) ( employeeDtos.size() / .75f ) + 1, 16 ) );
        for ( EmployeeDto employeeDto : employeeDtos ) {
            set.add( toEntity( employeeDto ) );
        }

        return set;
    }

    @Override
    public Set<EmployeeDto> toDtoSet(Set<Employee> employees) {
        if ( employees == null ) {
            return null;
        }

        Set<EmployeeDto> set = new LinkedHashSet<EmployeeDto>( Math.max( (int) ( employees.size() / .75f ) + 1, 16 ) );
        for ( Employee employee : employees ) {
            set.add( toDto( employee ) );
        }

        return set;
    }

    @Override
    public Set<EmployeeBenefitDto> toEmployeeBenefitDtoSet(Set<EmployeeBenefit> employeeBenefits) {
        if ( employeeBenefits == null ) {
            return null;
        }

        Set<EmployeeBenefitDto> set = new LinkedHashSet<EmployeeBenefitDto>( Math.max( (int) ( employeeBenefits.size() / .75f ) + 1, 16 ) );
        for ( EmployeeBenefit employeeBenefit : employeeBenefits ) {
            set.add( employeeBenefitToEmployeeBenefitDto( employeeBenefit ) );
        }

        return set;
    }

    @Override
    public Set<EmployeeBenefit> toEmployeeBenefitSet(Set<EmployeeBenefitDto> employeeBenefitDtos) {
        if ( employeeBenefitDtos == null ) {
            return null;
        }

        Set<EmployeeBenefit> set = new LinkedHashSet<EmployeeBenefit>( Math.max( (int) ( employeeBenefitDtos.size() / .75f ) + 1, 16 ) );
        for ( EmployeeBenefitDto employeeBenefitDto : employeeBenefitDtos ) {
            set.add( employeeBenefitDtoToEmployeeBenefit( employeeBenefitDto ) );
        }

        return set;
    }

    protected Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( employeeDto.getDirectManagerID() );

        return employee;
    }

    protected Department employeeDtoToDepartment(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Department department = new Department();

        department.setId( employeeDto.getDepartmentID() );

        return department;
    }

    protected Team employeeDtoToTeam(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Team team = new Team();

        team.setId( employeeDto.getTeamID() );

        return team;
    }

    private Integer employeeDirectManagerId(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Employee directManager = employee.getDirectManager();
        if ( directManager == null ) {
            return null;
        }
        Integer id = directManager.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer employeeDepartmentId(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Department department = employee.getDepartment();
        if ( department == null ) {
            return null;
        }
        Integer id = department.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer employeeTeamId(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Team team = employee.getTeam();
        if ( team == null ) {
            return null;
        }
        Integer id = team.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected BenefitDto benefitToBenefitDto(Benefit benefit) {
        if ( benefit == null ) {
            return null;
        }

        Integer id = null;
        String benefitName = null;
        String description = null;

        id = benefit.getId();
        benefitName = benefit.getBenefitName();
        description = benefit.getDescription();

        BenefitDto benefitDto = new BenefitDto( id, benefitName, description );

        return benefitDto;
    }

    protected EmployeeBenefitDto employeeBenefitToEmployeeBenefitDto(EmployeeBenefit employeeBenefit) {
        if ( employeeBenefit == null ) {
            return null;
        }

        EmployeebenefitId id = null;
        EmployeeDto employee = null;
        BenefitDto benefit = null;
        LocalDate enrollmentDate = null;

        id = employeeBenefit.getId();
        employee = toDto( employeeBenefit.getEmployee() );
        benefit = benefitToBenefitDto( employeeBenefit.getBenefit() );
        enrollmentDate = employeeBenefit.getEnrollmentDate();

        EmployeeBenefitDto employeeBenefitDto = new EmployeeBenefitDto( id, employee, benefit, enrollmentDate );

        return employeeBenefitDto;
    }

    protected Benefit benefitDtoToBenefit(BenefitDto benefitDto) {
        if ( benefitDto == null ) {
            return null;
        }

        Benefit benefit = new Benefit();

        benefit.setId( benefitDto.getId() );
        benefit.setBenefitName( benefitDto.getBenefitName() );
        benefit.setDescription( benefitDto.getDescription() );

        return benefit;
    }

    protected EmployeeBenefit employeeBenefitDtoToEmployeeBenefit(EmployeeBenefitDto employeeBenefitDto) {
        if ( employeeBenefitDto == null ) {
            return null;
        }

        EmployeeBenefit employeeBenefit = new EmployeeBenefit();

        employeeBenefit.setId( employeeBenefitDto.getId() );
        employeeBenefit.setEmployee( toEntity( employeeBenefitDto.getEmployee() ) );
        employeeBenefit.setBenefit( benefitDtoToBenefit( employeeBenefitDto.getBenefit() ) );
        employeeBenefit.setEnrollmentDate( employeeBenefitDto.getEnrollmentDate() );

        return employeeBenefit;
    }
}
