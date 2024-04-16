package com.alif.newsapplication.core.vm

import com.alif.core.common.Mapper


class BaseErrorMapper : Mapper<Throwable, String> {
    override fun map(data: Throwable): String {
        return data.message.toString()
    }
}
