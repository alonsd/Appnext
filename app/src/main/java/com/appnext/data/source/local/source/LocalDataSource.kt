package com.appnext.data.source.local.source

import com.appnext.data.source.local.dao.AppNextDao


class LocalDataSource(private val appNextDao: AppNextDao) {

    suspend fun getAppNextModel() =  appNextDao.getAppNextModel()

}