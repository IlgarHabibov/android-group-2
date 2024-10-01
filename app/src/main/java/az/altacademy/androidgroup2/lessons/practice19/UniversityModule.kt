package az.altacademy.androidgroup2.lessons.practice19

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object UniversityModule {

    @Provides
    @UniversityAddress
    fun provideUniAddress(): Address{
        return Address("Yasamal")
    }

    @Provides
    @TeacherAddress
    fun provideTeacherAddress(): Address{
        return Address("Xirdalan")
    }


    @Provides
    @StudentAddress
    fun provideStudentAddress(): Address{
        return Address("Sumqayit")
    }

}