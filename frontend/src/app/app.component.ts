import { Component, LOCALE_ID } from '@angular/core';
import '@angular/common/locales/global/es-UY';
import { RouterOutlet } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-root',
    standalone: true,
    imports: [
        RouterOutlet,
        CommonModule,
        HttpClientModule,
    ],
    providers: [{ provide: LOCALE_ID, useValue: 'es-UY' }],
    templateUrl: './app.component.html',
    styleUrl: './app.component.scss'
})
export class AppComponent {
}
