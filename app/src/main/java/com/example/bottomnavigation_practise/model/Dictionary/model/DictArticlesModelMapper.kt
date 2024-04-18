package com.example.bottomnavigation_practise.model.Dictionary.model

import com.alif.core.common.Mapper
import com.example.bottomnavigation_practise.model.Dictionary.model.dataSource.db.dictionary.entity.DictionaryEntity

class DictArticlesModelMapper : Mapper<DictArticlesModel, DictionaryEntity> {
    override fun map(data: DictArticlesModel): DictionaryEntity {
        return DictionaryEntity(
            wordTj = data.word_tj,
            wordRu = data.word_ru,
            wordEng = data.word_eng,
            sound = data.sound,
        )

    }
}