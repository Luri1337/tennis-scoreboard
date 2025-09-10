package dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import model.entity.FinishedMatch;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class MatchesPageDto {
    private final List<FinishedMatch> matches;
    private final double totalPages;
    private final int currentPage;
    private final String filter;
}
