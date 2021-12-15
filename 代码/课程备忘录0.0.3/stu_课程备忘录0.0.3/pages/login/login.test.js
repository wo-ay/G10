// uni-app自动化测试教程: uni-app自动化测试教程: https://uniapp.dcloud.io/collocation/auto/hbuilderx-extension/index

describe('test title', () => {

    let page;
    beforeAll(async () => {
        page = await program.currentPage();//获取当前page
        await page.waitFor(3000);
    });

    it('测试登录功能', async () => {
        const el = await page.$('.title');
        const titleText = await el.text();
        expect(titleText).toEqual('Hello');
    });
});
