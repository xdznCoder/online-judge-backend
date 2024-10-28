package cn.xdzn.oj.service.problem.interfaces.assembler;

import cn.xdzn.oj.service.problem.domain.Training.entity.po.Training;

import cn.xdzn.oj.service.problem.interfaces.dto.TrainingDTO;
import cn.xdzn.oj.service.problem.interfaces.dto.TrainingFrontDTO;

public class TrainingAssembler {
    private TrainingAssembler(){}
    public static TrainingFrontDTO toFrontDTO(Training training){
        return new TrainingFrontDTO()
                .setId(training.getId())
                .setTitle(training.getTitle())
                .setAuthor(training.getAuthor())
                .setAuth(training.getAuth())
                .setRank(training.getRank())
                .setCid(training.getCid())
                .setGmtModified(training.getGmtModified())
                .setCreateBy(training.getCreateBy());
        //TOFill
        //category
        //problemNum
        //ac
        //acRate
    }
    public static TrainingDTO toDTO(Training training){
        return new TrainingDTO()
                .setId(training.getId())
                .setTitle(training.getTitle())
                .setAuthor(training.getAuthor())
                .setAuth(training.getAuth())
                .setRank(training.getRank())
                .setCid(training.getCid());
    }
}

