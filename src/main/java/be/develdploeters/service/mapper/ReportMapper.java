package be.develdploeters.service.mapper;

import be.develdploeters.domain.Report;
import be.develdploeters.service.dto.ReportDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Report} and its DTO {@link ReportDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReportMapper extends EntityMapper<ReportDTO, Report> {

    Report toEntity(ReportDTO reportDTO);

    default Report fromId(Long id) {
        if (id == null) {
            return null;
        }
        Report report = new Report();
        report.setId(id);
        return report;
    }
}
