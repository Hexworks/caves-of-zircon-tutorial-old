import { browser, by, element } from 'protractor'

export class AppPage {
  navigateTo() {
    return browser.get('/')
  }

  getTitleText() {
    return element(by.css('koj-root h1')).getText()
  }
}
