package be.develdploeters.service;

import be.develdploeters.domain.Activity;
import be.develdploeters.domain.Report;
import be.develdploeters.repository.ReportRepository;
import be.develdploeters.service.dto.ReportDTO;
import be.develdploeters.service.dto.ReportOverviewDTO;
import be.develdploeters.service.mapper.ReportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Report}.
 */
@Service
@Transactional
public class ReportService {

    private final Logger log = LoggerFactory.getLogger(ReportService.class);

    private final ReportRepository reportRepository;

    private final ReportMapper reportMapper;

    private final ActivityService activityService;

    public ReportService(ReportRepository reportRepository,
                         ReportMapper reportMapper,
                         ActivityService activityService) {
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
        this.activityService = activityService;
    }

    /**
     * Save a report.
     *
     * @param reportDTO the entity to save.
     * @return the persisted entity.
     */
    public ReportDTO save(ReportDTO reportDTO) {
        log.debug("Request to save Report : {}", reportDTO);
        Report report = reportMapper.toEntity(reportDTO);
        report = reportRepository.save(report);
        return reportMapper.toDto(report);
    }

    /**
     * Get all the reports.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ReportDTO> findAll() {
        log.debug("Request to get all Reports");
        return reportRepository.findAll().stream()
            .map(reportMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one report by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ReportDTO> findOne(Long id) {
        log.debug("Request to get Report : {}", id);
        return reportRepository.findById(id)
            .map(reportMapper::toDto);
    }

    /**
     * Delete the report by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Report : {}", id);
        reportRepository.deleteById(id);
    }

    public ReportDTO findActivityReport(Long id) {
        Activity activity = activityService.findOne(id);

        if (activity.getReport() == null) {
            Report report =  new Report();
            activity.setReport(report);
            activity = activityService.save(activity);
        }

        return reportMapper.toDto(activity.getReport());
    }

    public List<ReportOverviewDTO> findLast5() {
        List<Activity> activities = activityService.findLastActivitiesWithReport();
        return activities.stream().map(this::mapActivityToOverviewDTO).collect(Collectors.toList());
    }

    private ReportOverviewDTO mapActivityToOverviewDTO(Activity activity) {
        ReportOverviewDTO dto = new ReportOverviewDTO();

        dto.setDate(activity.getDate());
        dto.setTitle(activity.getTitle());
        dto.setReport(activity.getReport().getReport());

        return dto;
    }
}
